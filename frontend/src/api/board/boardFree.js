import http from "@/api/http";

/**
 * 게시글 수정
 * @param boardNo
 * @param boardDto
 * @returns {Promise<void>}
 */
export async function updateFreeArticle(boardNo, boardDto) {
    return http.put('/free/modify/' + boardNo, boardDto)
}

/**
 * 게시글 삭제
 * @param boardNo
 * @returns {Promise<void>}
 */
export async function deleteArticle(boardNo) {
    return http.delete('/free/delete/' + boardNo);
}

/**
 * 자유게시판 검색
 * @returns {Promise<void>}
 * @param keyword
 * @param startDate
 * @param endDate
 */
export async function getFreeListWithQuery(keyword, startDate, endDate) {
    return http.get('/free', {
        params: {
            keyword: keyword,
            startDate: startDate,
            endDate: endDate
        }
    })
}

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
 * 수정페이지 게시글 조회 (조회수 증가 x)
 * @param boardNo
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function getModifyFreeArticle(boardNo) {
    return http.get('/free/modify/' + boardNo)
}

/**
 * 자유게시글 등록
 * @param boardDto
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function postArticle(boardDto) {
    return http.post('/free', boardDto);
}
