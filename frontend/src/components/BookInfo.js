
import React, { useState } from "react";
import { Card } from 'antd';
import './BookInfo.css';

function BookInfo({ data, onClick }) {
    const card_width = 140;
    const card_height = card_width * Math.SQRT2;
    const [imgWidth, setImageWidth] = useState(card_width);
    const [imgHeight, setImageHeight] = useState(card_height);

    var img = new Image();
    img.src = data.imageLink
    img.onload = function () {
        const width_ratio = card_width / img.width
        const height_ratio = card_height / img.height
        const ratio = Math.abs(1 - width_ratio) < Math.abs(1 - height_ratio) ? width_ratio : height_ratio;
        setImageWidth(img.width * ratio)
        setImageHeight(img.height * ratio)
    }

    return (
        <div onClick={() => onClick()}>
            <Card
                className="book-card"
                hoverable
                style={{ width: card_width, height: card_height }}
                cover={
                    <div className="book-image">
                        <img alt="a" src={data.imageLink} style={{ width: imgWidth, height: imgHeight }} />
                    </div>
                }
            >
            </Card>
        </div >
    );
}

export default BookInfo;