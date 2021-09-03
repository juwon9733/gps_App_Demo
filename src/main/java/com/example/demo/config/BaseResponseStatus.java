package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000 : Request 오류
     */
    // Common
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    EMPTY_USER_ID(false, 2004, "유저 아이디를 입력해주세요"),
    EMPTY_USER_PASSWD(false, 2005, "유저 비밀번호를 입력해주세요"),

    /**
     *3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    DUPLICATED_USER_ID(false, 3001, "중복되는 아이디입니다."),
    USER_ID_NOT_EXIST(false, 3002, "존재하지 않는 아이디입니다."),
    FAILED_TO_LOGIN_BY_PASSWD(false, 3003, "비밀번호가 옳바르지 않습니다."),

    /**
     * 4000 : Database, Server 오류
     */
    // Common
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    PASSWORD_ENCRYPTION_ERROR(false, 4010, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4015, "비밀번호 복호화에 실패하였습니다."),



    // 5000 : 필요시 만들어서 쓰세요
    ERROR_ONE(false, 5001, "카카오 소셜 로그인 에러1"),
    ERROR_TOW(false, 5002, "카카오 소셜 로그인 에러2"),

    // 6000 : 필요시 만들어서 쓰세요
    DELETED_TOKEN(false, 5003, "삭제된 토큰으로, 재 로그인 해야합니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
