import { combineReducers } from 'redux'
import bookinfos from './bookinfos'
import account from './account'
import errors from './errors'

export default combineReducers({
    account,
    bookinfos,
    errors
})