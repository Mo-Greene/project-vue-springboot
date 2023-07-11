import http from "@/api/http";

/**
 * 로그인 api
 * @param username
 * @param password
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function login(username, password) {

    const loginDto = {
        username: username,
        password: password
    }
    return await http.post('/api/login', loginDto);
}

/**
 * 토큰을 사용해 유저 정보 api
 * @param token
 * @returns {Promise<void>}
 */
export async function getUserInfo(token) {
    return await http.get('/api/users', {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });
}
