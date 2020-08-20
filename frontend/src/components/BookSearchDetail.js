import React from 'react'
import { Modal } from 'antd';
import './BookSearchDetail.css'

function BookSearchDetail({ bookInfo, visible, handleCancel, handleCreate }) {
    return (
        < div >
            <Modal
                title={bookInfo.title}
                visible={visible}
                onOk={handleCreate}
                onCancel={handleCancel}
            >
                <div className="modal-contents">
                    <img alt="a" src={bookInfo.imageLink} />
                    <div className="details">
                        <div>{bookInfo.publishedDate}</div>
                    </div>
                </div>
                <p>説明</p>
                <div className="description">{bookInfo.description}</div>
            </Modal>
        </div >
    );
}


export default BookSearchDetail;