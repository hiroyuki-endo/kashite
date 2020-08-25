import React, { useState } from 'react'
import { useDispatch } from "react-redux";
import { withRouter } from 'react-router-dom';
import { Button, Input } from 'antd';
import useReactRouter from 'use-react-router';
import SubTitle from './SubTitle'
import './SignIn.css'

function SignIn() {
    const dispatch = useDispatch();
    const { history, location, match } = useReactRouter();

    function login() {
        dispatch({ type: "FETCH_ACCOUNT" })
        history.push('/kashite/main')
    }

    return (
        <div>
            <div className="inner-title">
                <SubTitle name={"ログイン"} />
            </div>
            <div className="login">
                <Input className="login-user-name" placeholder="ユーザーID" />
                <Input.Password className="login-password" placeholder="パスワード" />
                <Button className="login-button" type="primary" onClick={v => login()}>ログイン</Button>
            </div>
        </div>
    );
}


export default withRouter(SignIn);