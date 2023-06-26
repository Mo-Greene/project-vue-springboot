import http from "@/api/http";

/**
 * 공지게시판 조회
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function getNoticeList() {
    return http.get('/notice')
}
