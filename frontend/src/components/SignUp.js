import React from 'react'
import { useDispatch } from "react-redux";
import { withRouter } from 'react-router-dom';
import { Button, Input } from 'antd';
import useReactRouter from 'use-react-router';
import './SignUp.css'

function SignUp() {
    const dispatch = useDispatch();
    const { history } = useReactRouter();

    function login() {
        dispatch({ type: "FETCH_ACCOUNT" })
        history.push('/kashite/main')
    }

    return (
        <div>
            <div className="signup">
                <Input className="signup-user-name" placeholder="ユーザーID" />
                <Input className="login-user-name" placeholder="表示名" />
                <Input.Password className="signup-password" placeholder="パスワード" />
                <Button className="signup-button" type="primary" onClick={v => login()} > アカウント作成 </Button>
            </div>
        </div>
    );
}


export default withRouter(SignUp);