import NavBar from "../components/NavBar";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import styled from "styled-components";
import { loginInfo } from '../App.js';
import {useAtom} from 'jotai';
import { useEffect } from 'react';
import {PlayCircleOutlined} from '@ant-design/icons';
import {makeGraph, D3} from './Graph.js';

const Main = () => {
  const [user,setUser ] = useAtom(loginInfo);
  // callback, dependency array
  useEffect(() => {console.log(user)}, [user])
  if(!user){
    return (
        <div>LOADING</div>
    );
  }

  return (
    <>
      <NavBar />
      <h1>{user.name}</h1>
      <div>메인 페이지</div>

      <div>
      <InputWrapper>
        <Button
           variant = "text"
           startIcon = { <PlayCircleOutlined />}>
        </Button>
       </InputWrapper>
      </div>

      <div>
        <InputWrapper>

         </InputWrapper>
        </div>
    </>
  );
  // Main component

  // api : /api/work/start/{member_id}
  // 시작 버튼을 누르면 현재 시간을 전송한다.
  // 제약 조건 : 지점의 위도, 경도와 현재 위치의 위도 경도가 0.006 차이 미만인 경우만 누를 수 있다.
  // <Button>start</Button>

  // api : /api/work/end/{member_id}
  // 종료 버튼을 누르면 현재 시간을 전송한다.
  // <Button>end</Button>

};
export default Main;

const InputWrapper = styled.div`
  max-width: 500px;
  text-align: center;
  margin: 30px auto;
`;
