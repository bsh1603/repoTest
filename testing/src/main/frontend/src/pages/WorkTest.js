import NavBar from "../components/NavBar";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import styled from "styled-components";
import { loginInfo } from '../App.js';
import {useAtom} from 'jotai';
import { useEffect } from 'react';

const WorkTest = () => {
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
      <div>근무 조회 테스트 페이지</div>

    <div>
      <InputWrapper>
        <h1>입력</h1>
       </InputWrapper>
    </div>

    </>
  );
};

export default WorkTest;

const InputWrapper = styled.div`
  max-width: 800px;
  text-align: center;
  margin: 30px auto;
`;