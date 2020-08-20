import React, { useState } from 'react'
import { useDispatch, useSelector } from "react-redux";
import { Button, List, Input } from 'antd';
import BookInfo from './BookInfo'
import SubTitle from './SubTitle'
import { fetchBookInfos } from '../actions/SearchBookAction'
import { registerBook } from '../actions/RegisterBookAction'
import BookSearchDetail from './BookSearchDetail'
import {
    CloseCircleOutlined,
    SearchOutlined,
} from '@ant-design/icons';
import axios from 'axios';
import './BookSearch.css';

function BookSearch() {


    // react
    const [title, setTitle] = useState();
    const [author, setAuthor] = useState();
    const [bookInfo, setBookInfo] = useState({});
    const [modalVisible, setModalVisible] = useState(false);
    // redux
    const dispatch = useDispatch();
    const bookInfos = useSelector(state => state.bookinfos);
    const account = useSelector(state => state.account);

    function onItemClick(item) {
        setBookInfo(item)
        setModalVisible(true)
    }

    function detailOkHandle() {
        dispatch(registerBook(bookInfo.id, account.id))
        setModalVisible(false)
    }

    return (
        <div className="book-search">
            <div className="inner-title">
                <SubTitle name={"本検索"} />
            </div>
            <div className="book-info-search">
                <div className="input-title"><Input placeholder="タイトル" onChange={e => setTitle(e.target.value)} /></div>
                <div className="input-author"><Input placeholder="著者" onChange={e => setAuthor(e.target.value)} /></div>
                <Button className="book-search-button" type="primary" onClick={v => dispatch(fetchBookInfos(title, author))} shape="circle" icon={<SearchOutlined />} />
                <Button className="book-search-button" type="danger" onClick={v => dispatch({ type: "CLEAR_BOOKINFOS" })} shape="circle" icon={<CloseCircleOutlined />} />
            </div>

            <div className="book-contents">
                <List
                    grid={{
                        gutter: 12
                    }}
                    dataSource={bookInfos}
                    renderItem={item => (
                        <div>
                            <List.Item>
                                <BookInfo data={item} onClick={e => onItemClick(item)} />
                            </List.Item>
                        </div>
                    )}
                />
            </div>
            <BookSearchDetail
                bookInfo={bookInfo}
                visible={modalVisible}
                handleCreate={v => detailOkHandle()}
                handleCancel={e => setModalVisible(false)}
            >
            </BookSearchDetail>
        </div>
    );
}


export default BookSearch;