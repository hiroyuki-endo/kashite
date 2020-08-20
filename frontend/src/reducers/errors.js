export default function account(state = [], action) {

    console.info(action)

    switch (action.type) {
        case "ADD_ERROR":
            return [...state, action.payload];
        default:
            return state
    }
}