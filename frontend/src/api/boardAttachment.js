import http from "@/api/http";

/**
 * 자료실 조회
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function getAttachmentList() {
    return http.get('/attachment')
}
