package com.mogreene.backend.config.responseApi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @name : ApiResponseDTO
 * @author : Mo-Greene
 * @date : 2023/06/14
 * 공통 response api
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDTO<T> {

    //http status
    private HttpStatus httpStatus;

    //데이터
    private T data;

}
