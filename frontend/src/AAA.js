import React, { useState } from 'react'
import { Input, Button, Row, Col, Space } from 'antd';
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

function AAA() {

    const [prefecture, setPrefecture] = useState("");
    const [name, setName] = useState("");
    const [direction, setDirection] = useState("");
    const [type, setType] = useState("");
    const [level, setLevel] = useState("");

    function test() {
        const obj = {}
        obj.prefecture = prefecture
        obj.name = name
        obj.direction = direction
        obj.type = type
        obj.level = level
        return obj;
    }

    function post(data) {
        axios.post('surf-points', data).then(response => {
            console.log('body:', response.data);
        });
    }

    return (
        <div className="aaa">
            <Row gutter={[8, 8]}>
                <Col>
                    <Space>
                        <div className="input_label">都道府県</div>
                        <Input defaultValue={prefecture} onChange={v => setPrefecture(v.target.value)} />
                    </Space>
                </Col>
                <Col>
                    <Space>
                        <div className="input_label">ポイント名</div>
                        <Input defaultValue={name} onChange={v => setName(v.target.value)} />
                    </Space>
                </Col>
            </Row>
            <Row gutter={[8, 16]}>
                <Col>
                    <Space>
                        <div className="input_label">海岸の向き</div>
                        <Input defaultValue={direction} onChange={v => setDirection(v.target.value)} />
                    </Space>
                </Col>
                <Col>
                    <Space>
                        <div className="input_label">地形</div>
                        <Input defaultValue={type} onChange={v => setType(v.target.value)} />
                    </Space>
                </Col>
                <Col>
                    <Space>
                        <div className="input_label">レベル</div>
                        <Input defaultValue={level} onChange={v => setLevel(v.target.value)} />
                    </Space>
                </Col>
            </Row>
            <Row gutter={[8, 8]}>
                <Col>
                    <Button onClick={v => console.log(test())}>キャンセル</Button>
                </Col>
                <Col>
                    <Button onClick={v => post(test())}>登録</Button>
                </Col>
            </Row>
        </div >
    );
}

export default AAA;