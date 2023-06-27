import http from "@/api/http";

/**
 * 자유게시판 조회
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function getFreeList() {
    return http.get('/free')
}

/**
 * 자유게시글 조회
 * @param boardNo
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function getFreeArticle(boardNo) {
    return http.get('/free/' + boardNo)
}

/**
 * 자유게시글 등록
 * @param boardDto
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function postArticle(boardDto) {
    return http.post('/free', boardDto);
}
