import React, { useState } from 'react'
import { Button, List, Input } from 'antd';
import './SubTitle.css';

function SubTitle({ name, onClickOk, onClickCancel }) {

    return (
        <div className="sub-title">
            <div>{name}</div>
        </div>
    );
}


export default SubTitle;