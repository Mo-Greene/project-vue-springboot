import http from "@/api/http";

/**
 * 게시글 수정
 * @param boardNo
 * @param formData
 * @returns {Promise<void>}
 */
export async function updateAttachmentArticle(boardNo, formData) {
    return http.put('/attachment/modify/' + boardNo, formData, {
        headers: {'Content-Type': 'multipart/form-data'}
    })
}

/**
 * 수정페이지 조회
 * @param boardNo
 * @returns {Promise<void>}
 */
export async function getModifyAttachmentArticle(boardNo) {
    return http.get('/attachment/modify/' + boardNo)
}

/**
 * 게시글 삭제
 * @param boardNo
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function deleteArticle(boardNo) {
    return http.delete('/attachment/delete/' + boardNo)
}


/**
 * 자료실 검색
 * @returns {Promise<void>}
 * @param params
 */
export async function getAttachmentListWithQuery(params) {
    return http.get('/attachment', { params })
}

/**
 * 자료실 조회
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function getAttachmentList() {
    return http.get('/attachment')
}

/**
 * 자료실 상세조회
 * @param boardNo
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function getAttachmentArticle(boardNo) {
    return http.get('/attachment/' + boardNo)
}

/**
 * 자료실 등록
 * @param formData
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function postArticle(formData) {
    return http.post('/attachment', formData, {
        headers: {'Content-Type': 'multipart/form-data'}
    })
}
