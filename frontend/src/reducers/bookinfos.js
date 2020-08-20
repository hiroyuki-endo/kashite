export default function bookinfos(state = [], action) {

    switch (action.type) {
        case "FETCH_BOOKINFOS":
            return action.payload
        case "CLEAR_BOOKINFOS":
            return []
        default:
            return state
    }
}