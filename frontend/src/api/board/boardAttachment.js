import http from "@/api/http";

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
