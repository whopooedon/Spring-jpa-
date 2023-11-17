import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function LoginForm() {
    const [credentials, setCredentials] = useState({
        userid: '',
        userpw: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCredentials({
            ...credentials,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(''); // 에러 메시지 초기화

        try {
            const response = await axios.post('http://localhost:8080/api/loginAction', credentials, { withCredentials: true });
            if (response.data.success) {
                sessionStorage.setItem('LOGIN_USER_ID', credentials.userid);
                // 로그인 성공 시 메인 페이지로 리다이렉션
                navigate('/main');
            } else {
                // 서버에서 로그인 실패 응답을 받았을 경우
                setError('로그인에 실패했습니다. 아이디와 비밀번호를 확인해 주세요.');
            }
        } catch (err) {
            // 요청 중 에러가 발생한 경우
            setError('로그인 중 문제가 발생했습니다. 나중에 다시 시도해 주세요.');
        }
    };

    return (
        <div>
            <h2>로그인</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>아이디:</label>
                    <input
                        type="text"
                        name="userid"
                        value={credentials.userid}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label>비밀번호:</label>
                    <input
                        type="password"
                        name="userpw"
                        value={credentials.userpw}
                        onChange={handleChange}
                    />
                </div>
                {error && <div className="error">{error}</div>}
                <button type="submit">로그인</button>
            </form>
        </div>
    );
}

export default LoginForm;
