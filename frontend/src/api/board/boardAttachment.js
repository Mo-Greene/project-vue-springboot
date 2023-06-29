import http from "@/api/http";

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
 * @param keyword
 * @param startDate
 * @param endDate
 * @returns {Promise<void>}
 */
export async function getAttachmentListWithQuery(keyword, startDate, endDate) {
    return http.get('/attachment', {
        params: {
            keyword: keyword,
            startDate: startDate,
            endDate: endDate
        }
    })
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
