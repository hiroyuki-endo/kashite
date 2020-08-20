import { notification } from 'antd';

const errorNotification = (error) => {

    if (!error) {
        return;
    }

    notification.error({
        message: error.title,
        description: error.message,
        duration: 3
    });
}

export default errorNotification;