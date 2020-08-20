export default function account(state = {}, action) {

    switch (action.type) {
        case "FETCH_ACCOUNT":
            return { id: "test", isAuth: !state.isAuth };
        default:
            return state
    }
}