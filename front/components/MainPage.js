import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

function MainPage() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [userId, setUserId] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const savedUserId = sessionStorage.getItem('LOGIN_USER_ID');
        if (savedUserId) {
            setIsLoggedIn(true);
            setUserId(savedUserId);
        }
    }, []);

    const handleLogout = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/logout');
            if (response.data.success) {
                // 세션 스토리지를 비우고 상태를 업데이트합니다.
                sessionStorage.removeItem('LOGIN_USER_ID');
                setIsLoggedIn(false);
                setUserId('');
                navigate('/main'); // 메인 페이지로 리다이렉션
            }
        } catch (error) {
            console.error('로그아웃 처리 중 에러가 발생했습니다.', error);
        }
    };

    const handleDelete = async () => {
        if (window.confirm("회원탈퇴 하시겟습니까?")) {
            try {
                const response = await axios.delete(`http://localhost:8080/api/user/delete/${userId}`);
                alert(response.data.message);
                sessionStorage.removeItem('LOGIN_USER_ID');
                setIsLoggedIn(false);
                setUserId('');
                navigate('/main');
            } catch (error) {
                console.error("Error deleting user data:", error);
                alert("Failed to delete user.");
            }
        }
    };

    return (
        <div>
            <h2>메인 페이지에 오신 것을 환영합니다.</h2>
            {isLoggedIn ? (
                <div>
                    <p>{userId}님 로그인되었습니다.</p>
                    <Link to="/userupdate">회원정보수정</Link>
                    <br />
                    <Link to="/myroute">내 경로 보기</Link>
                    <br />
                    <button onClick={handleLogout}>로그아웃</button>
                    <br />
                    <button onClick={handleDelete}>회원탈퇴</button>
                </div>
            ) : (
                <div>
                    <Link to="/login">로그인</Link>
                    <br />
                    <Link to="/register">회원가입</Link>
                </div>
            )
            }
            < br />
            <Link to="/mapbox">따릉이 찾으러가기</Link>
        </div >
    );
}

export default MainPage;
