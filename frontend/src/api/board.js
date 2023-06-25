import http from "@/api/http";

export async function getFreeList() {
    return http.get('/free')
}
