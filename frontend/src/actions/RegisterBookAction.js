import axios from 'axios';
import { useDispatch, useSelector } from "react-redux";

const config = {
    headers: { 'Access-Control-Allow-Origin': '*' }
}

export const registerBook = (bookInfoId, lenderId) => (dispatch) => {

    const request = {}
    request.id = bookInfoId
    const configs = { ...config }
    return axios.post(`http://localhost:8181/bookinfos`, request, configs)
        .then(response => {
            dispatch(fuga(bookInfoId, lenderId))
        })
        .catch(
            ({ response }) => {
                console.info("error")
                console.info(response)
                dispatch({ type: "ADD_ERROR", payload: { title: response.data.error, code: response.data.status, message: response.data.message } })
            }
        )
}

export const fuga = (bookInfoId, lenderId) => (dispatch) => {
    const request = {}
    request.bookInfoId = bookInfoId
    request.lenderId = lenderId
    const configs = { ...config }

    return axios.post(`http://localhost:8181/books/register`, request, configs)
        .then(response => {
            dispatch({
                type: "REGISTER_BOOKINFO",
                payload: response.data,
            })
        })
}

