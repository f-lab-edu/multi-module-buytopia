package com.zeroskill.common.dto.request;

public record PaymentResultRequest(
        String orderId,           // 주문의 고유 식별자
        String status,            // 결제 상태: COMPLETED, FAILED, CANCELED
        String paymentId,         // 결제 식별자
        Long amount,              // 결제된 금액
        String currency,          // 통화 단위 (예: KRW)
        String paymentMethod,     // 결제 방법 (예: CREDIT_CARD)
        String transactionTime,   // 결제 시각 (ISO 8601 형식 문자열)
        String merchantId,        // 고객 식별자
        String resultCode,      // 결과 코드
        String errorMessage,      // 오류 메시지 (결제 실패 시에만 사용)
        String cancelReason,      // 취소 사유 (결제 취소 시에만 사용)
        String message            // 결제 상태에 따른 메시지
) {
}

//{
//        "orderId": "13eaa710-dbe8-4a07-af30-4619f12178f0",
//        "status": "COMPLETED",  // 가능한 값: COMPLETED, FAILED, CANCELED
//        "paymentId": "pay_987654321",
//        "amount": 15000,
//        "currency": "KRW",
//        "paymentMethod": "CREDIT_CARD",
//        "transactionTime": "2024-09-03T10:00:00Z",
//        "merchantId": "mer_123456",
//        "resultCode": "0000",  // 결제 완료 시에만 포함
//        "errorMessage": "잔액이 부족합니다.",  // 결제 실패 시에만 포함
//        "cancelReason": "사용자 요청",  // 결제 취소 시에만 포함
//        "message": "결제가 성공적으로 완료되었습니다." // 결제 상태에 따른 메시지
//}

