import React, { useState } from 'react'
import { useDispatch } from "react-redux";
import { withRouter } from 'react-router-dom';
import { Button, Input } from 'antd';
import useReactRouter from 'use-react-router';
import SubTitle from './SubTitle'
import './Login.css'

function Login() {
    const dispatch = useDispatch();
    const { history, location, match } = useReactRouter();

    function login() {
        dispatch({ type: "FETCH_ACCOUNT" })
        history.push('/kashite')
    }

    return (
        <div>
            <div className="inner-title">
                <SubTitle name={"ログイン"} />
            </div>
            <div className="login">
                <Input className="login-user-name" placeholder="ユーザー名"></Input>
                <Input className="login-password" placeholder="パスワード"></Input>
                <Button className="login-button" type="primary" onClick={v => login()}>ログイン</Button>
            </div>
        </div>
    );
}


export default withRouter(Login);