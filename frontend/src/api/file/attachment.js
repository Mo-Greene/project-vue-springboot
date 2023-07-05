import http from "@/api/http";

/**
 * 파일 다운로드
 * @param fileNo
 * @returns {Promise<*>}
 */
export async function downloadFile(fileNo) {
    return http({
        url: '/files/download/' + fileNo,
        method: 'GET',
        responseType: 'blob'
    })
}

/**
 * 첨부파일 삭제
 * @param fileNo
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function deleteFile(fileNo) {
    return http.delete('/files/delete/' + fileNo)
}
