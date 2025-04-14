package com.example.DaLtdd.controller;

import com.example.DaLtdd.HMACUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class PaymentController {

    @Value("${vnp_TmnCode}")
    private String vnp_TmnCode;

    @Value("${vnp_HashSecret}")
    private String vnp_HashSecret;

    @Value("${vnp_Url}")
    private String vnp_Url;

    @Value("${vnp_ReturnUrl}")
    private String vnp_ReturnUrl;

    @Value("${vnp_IpnUrl}")
    private String vnp_IpnUrl;

    @GetMapping("/api/vnpay/create-payment")
    public ResponseEntity<?> createPayment(@RequestParam("amount") String amount, HttpServletRequest req) {
        String orderId = UUID.randomUUID().toString();
        String orderInfo = "Thanh toan ve xem phim";
        String bankCode = "NCB";
        String vnpTxnRef = orderId;
        String vnp_IpAddr = req.getRemoteAddr();

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(Integer.parseInt(amount) * 100));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnpTxnRef);
        vnp_Params.put("vnp_OrderInfo", orderInfo);
        vnp_Params.put("vnp_OrderType", "billpayment");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_CreateDate", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        vnp_Params.put("vnp_IpnUrl", vnp_IpnUrl);
        // Optional bank code
        vnp_Params.put("vnp_BankCode", bankCode);

        // Sắp xếp các key theo thứ tự alphabet
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && fieldValue.length() > 0) {
                // DÙNG UTF-8 Ở ĐÂY
                hashData.append(fieldName).append('=')
                        .append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8)).append('&');
                query.append(fieldName).append('=')
                        .append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8)).append('&');
            }
        }

        // Bỏ ký tự & cuối cùng
        String queryUrl = query.substring(0, query.length() - 1);
        String hashDataFinal = hashData.substring(0, hashData.length() - 1);

        // Tạo secure hash
        String secureHash = HMACUtil.hmacSHA512(vnp_HashSecret, hashDataFinal);
        queryUrl += "&vnp_SecureHash=" + secureHash;

        // Tạo full URL thanh toán
        String paymentUrl = vnp_Url + "?" + queryUrl;

        // Debug log nếu cần
        System.out.println("✅ Payment URL: " + paymentUrl);
        System.out.println("🔐 Hash data: " + hashDataFinal);
        System.out.println("🔐 Secure hash: " + secureHash);

        // Trả về cho Android app
        return ResponseEntity.ok(Map.of("paymentUrl", paymentUrl));
    }
}
