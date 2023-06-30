import http from "@/api/http";

/**
 * 게시글 수정
 * @param boardNo
 * @param boardDto
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function updateNoticeArticle(boardNo, boardDto) {
    return http.put('/notice/modify/' + boardNo, boardDto)
}

/**
 * 수정페이지 게시글 조회 (조회수 증가 x)
 * @param boardNo
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function getModifyNoticeArticle(boardNo) {
    return http.get('/notice/modify/' + boardNo)
}

/**
 * 게시글 삭제
 * @param boardNo
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function deleteArticle(boardNo) {
    return http.delete('/notice/delete/' + boardNo)
}

/**
 * 공지게시판 검색
 * @returns {Promise<void>}
 * @param params
 */
export async function getNoticeListWithQuery(params) {
    return http.get('/notice', { params })
}


/**
 * 공지게시판 조회
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function getNoticeList() {
    return http.get('/notice')
}

/**
 * 공지게시글 상세조회
 * @param boardNo
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function getNoticeArticle(boardNo) {
    return http.get('/notice/' + boardNo)
}

/**
 * 공지게시글 등록
 * @param boardDto
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function postArticle(boardDto) {
    return http.post('/notice', boardDto);
}
