import http from "@/api/http";

/**
 * 댓글 list
 * @param boardNo
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function getReplyList(boardNo) {
    return http.get('/reply/' + boardNo)
}

/**
 * 댓글 등록
 * @param boardNo
 * @param reply
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function postReply(boardNo, reply) {
    return http.post('/reply/' + boardNo, reply)
}
