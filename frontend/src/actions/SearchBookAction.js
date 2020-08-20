import axios from 'axios';

export const fetchBookInfos = (title, author) => (dispatch) => {

    console.info("fetchBookInfos")
    console.info("title:" + title + " author:" + author)

    const config = {
        headers: { 'Access-Control-Allow-Origin': '*' }
    }
    const params = {}
    params.title = title
    params.author = author
    const configs = { ...config, params: params }
    return axios.get(`http://localhost:8181/bookinfos/search`, configs)
        .then(response => {
            dispatch({
                type: "FETCH_BOOKINFOS",
                payload: response.data,
            })
        })
}
