import http from "@/api/http";

/**
 * 자유게시판 조회
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function getFreeList() {
    return http.get('/free')
}
