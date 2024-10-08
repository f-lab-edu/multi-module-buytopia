# Error Codes

시스템에서 발생할 수 있는 다양한 에러 코드와 관련 메시지들을 카테고리별로 정리한 표입니다.

| **카테고리**      | **에러 코드** | **에러 설명**            | **내부 메시지**                     | **외부 메시지**                         |
|-------------------|---------------|-------------------------|-------------------------------------|-----------------------------------------|
| **Validation**    | V-01          | 빈 필드                 | 필수 입력 필드가 비어 있음          | 필수 입력란을 채워주세요.               |
|                   | V-02          | 이메일 형식 오류        | 이메일 형식이 RFC 5322를 따르지 않음 | 올바른 이메일 주소를 입력해주세요.      |
|                   | V-03          | 비밀번호 불일치         | 비밀번호와 비밀번호 확인이 일치하지 않음 | 비밀번호가 일치하지 않습니다.         |
| **Authentication**| A-01          | 인증 실패               | 사용자 인증 실패, 유효하지 않은 자격 증명 | 로그인에 실패했습니다. 자격 증명을 확인하세요. |
|                   | A-02          | 권한 없음               | 필요한 권한이 없음                  | 이 작업을 수행할 권한이 없습니다.       |
| **Duplicate**     | D-01          | 중복 데이터             | 이미 존재하는 데이터, 예: 이메일 중복 | 입력한 데이터가 이미 존재합니다.       |
|                   | D-02          | 중복 사용자 이름        | 이미 존재하는 사용자 이름           | 이 사용자 이름은 이미 사용 중입니다.    |
| **Database**      | DB-01         | 데이터베이스 연결 실패  | 데이터베이스 서버에 연결할 수 없음    | 서버 문제로 인해 서비스를 이용할 수 없습니다. |
|                   | DB-02         | 데이터 무결성 위반      | 데이터 무결성 제약 조건 위반         | 데이터 처리 중 오류가 발생했습니다.    |
| **Server**        | S-01          | 내부 서버 오류          | 예상치 못한 서버 오류 발생           | 서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요. |
|                   | S-02          | 서비스 이용 불가        | 서버가 요청을 처리할 수 없음         | 현재 서비스를 이용할 수 없습니다.      |
| **Network**       | N-01          | 네트워크 연결 실패      | 외부 네트워크 연결 불가             | 네트워크 문제로 요청을 처리할 수 없습니다. |
|                   | N-02          | 연결 시간 초과          | 서버 연결 시간 초과                 | 서버 응답 시간이 초과되었습니다. 다시 시도해주세요. |
| **Product**       | P-01          | 재고 부족               | 요청된 상품의 재고가 부족함         | 선택한 상품이 재고가 부족합니다.       |
| **Payment**       | PMT-01        | 결제 실패               | 결제 프로세스 중 오류 발생           | 결제 처리 중 문제가 발생했습니다. 다시 시도해주세요. |

## 설명

- **카테고리**: 에러가 속한 카테고리를 나타냅니다. 각 카테고리는 관련된 에러들을 그룹화합니다.
- **에러 코드**: 각 에러에 대한 고유 식별자입니다.
- **에러 설명**: 에러의 간단한 설명입니다.
- **내부 메시지**: 개발자나 시스템 관리자가 에러의 원인을 이해하는 데 도움이 되는 구체적인 설명입니다.
- **외부 메시지**: 최종 사용자에게 표시될 수 있는 친숙한 에러 메시지입니다.
