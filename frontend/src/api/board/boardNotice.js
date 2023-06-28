import http from "@/api/http";

/**
 * 공지게시판 검색
 * @param keyword
 * @param startDate
 * @param endDate
 * @returns {Promise<void>}
 */
export async function getNoticeListWithQuery(keyword, startDate, endDate) {
    return http.get('/notice', {
        params: {
            keyword: keyword,
            startDate: startDate,
            endDate: endDate
        }
    })
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
