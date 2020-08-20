import React from 'react';
import { useSelector } from "react-redux";
import { Redirect } from 'react-router-dom';

function Auth({ children }) {
    const account = useSelector(state => state.account);

    return account.isAuth ? children() : <Redirect to={'/login'} />;
}

export default Auth;