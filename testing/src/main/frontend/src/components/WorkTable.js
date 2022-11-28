import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";

import { useEffect } from "react";
import axios from "axios";
import { useRecoilValue, useRecoilState } from "recoil";
import { memberState, userState, workState } from "../recoil/atom";
import moment from 'moment';

export default function BasicTable({ rows }) {
  const user = useRecoilValue(userState);
  const [member, setMember] = useRecoilState(memberState);
  const [work, setWork] = useRecoilState(workState);

    useEffect(() => {
      axios.get(`/api/member/my/${JSON.parse(localStorage.getItem("user")).id}`).then((response) => {
        setWork(response.data);
      });
    }, []);

  function getStart(work_start_time){
    var startTime = moment(work_start_time).utc().subtract(9, "hours").format("HH:mm");
    return startTime;
  }

  function getEnd(work_end_time){
    var endTime = moment(work_end_time).utc().subtract(9, "hours").format("HH:mm");
    return endTime;
  }

  function getTest(work_time){
    var workTime = moment(work_time).utc().subtract(9, "hours").format("HH:mm");
    return workTime;
  }

  function getYearMonth(work_start_time){
    var days = moment(work_start_time).utc().subtract(9, "hours").format("YYYY-MM-DD");
    return days;
  }

  function getHours(work_time){
    var hours = moment(work_time).utc().subtract(9, "hours").format("HH");
    return hours;
  }

  function getMinutes(work_time){
      var minutes = moment(work_time).utc().subtract(9, "hours").format("mm");
      return minutes;
  }

  console.log("work 사이즈 알아보기");
  console.log(Object.keys(work.works).length);

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell align="center">no.</TableCell>
            <TableCell align="center">날짜</TableCell>
            <TableCell align="center">시작시간</TableCell>
            <TableCell align="center">종료시간</TableCell>
            <TableCell align="center">일한시간</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
              <TableRow
              key={row.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
              <TableCell component="th" scope="row" align="center">
                {row.id}
              </TableCell>
              <TableCell align="center">{getYearMonth(row.work_start_time)}</TableCell>
              <TableCell align="center">{getStart(row.work_start_time)}</TableCell>
              <TableCell align="center">{getEnd(row.work_end_time)}</TableCell>
              <TableCell align="center">{getHours(row.work_time)}시간 {getMinutes(row.work_time)}분</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}