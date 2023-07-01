import http from "@/api/http";

export async function postReply(boardNo, reply) {
    return http.post('/reply/' + boardNo, reply)
}
