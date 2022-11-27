//import React, { useEffect} from 'react';
//import * as d3 from 'd3';
//import { useNavigate } from "react-router-dom";
//import TextField from "@mui/material/TextField";
//import Button from "@mui/material/Button";
//import styled from "styled-components";
//
//const Graph = () => {
//
//  // 차트를 그리는 함수
//  const makeGraph = ()=>{
//
//    // 캔버스
//    const svg = d3.select('body')
//                   .append('svg') // 가상의 캔버스를 그린다
//                   .attr('width', 400) // 캔버스의 넓이
//                   .attr('height', 400); // 캔버스의 높이
//    const data = [40, 100, 80, 140, 60, 70]; // 표현해야 할 input data
//
//    svg
//      .selectAll('rect') // 사각형 모양, 바 차트를 의미
//      .data(data) // 데이터를 집어 넣고
//      .enter() // 데이터를 순회
//      .append('rect')
//      .attr('x', 10) // 도형을 그릴 x좌표의 값
//      .attr('y', (data, idx) => idx * (20 + 1)) // 도형을 그릴 y좌표의 값
//      .attr('width', (date) => data) // 각각의 데이터를 바 차트의 가로 길이로
//      .attr('height', 20) // 바 차트의 높이는 20
//      .attr('fill', 'blue') // 도형에 색깔을 부여, default는 black
//      .attr('class', 'bar-chart') // 직접 class를 부여하여 css로 처리도 가능
//  }
//
//  useEffect(()=>{
//    makeGraph();
//  },[])
//
//  return(
//
//      <TextField
//        id="outlined-basic"
//        label="이메일"
//        variant="outlined"
//        fullWidth={true}
//      />
//   )
//}
//
//export default Graph;
import React, { useEffect } from 'react';
import * as d3 from 'd3';

const D3 = () => {
  useEffect(() => {
    makeGraph();
  }, []);

  const makeGraph = () => {
    // setting canvas
    const width = 400;
    const height = 400;
    const margin = { top: 40, left: 40, bottom: 40, right: 40 };

    const svg = d3.select('body').append('svg').attr('width', width).attr('height', height);

    // data
    const data = [
      { month: '1월', value: 40, color: 'red' },
      { month: '2월', value: 10, color: 'orange' },
      { month: '3월', value: 60, color: 'yellow' },
      { month: '4월', value: 95, color: 'green' },
      { month: '5월', value: 30, color: 'blue' },
      { month: '6월', value: 78, color: 'indigo' },
    ];

    // setting axis
    const x = d3
      .scaleBand()
      .domain(data.map((d) => d.month))
      .range([margin.left, width - margin.right]);

    const y = d3
      .scaleLinear()
      .domain([0, d3.max(data, (d) => d.value)])
      .nice()
      .range([height - margin.bottom, margin.top]);

    const xAxis = (g) => {
      return g
        .attr('transform', `translate(0, ${height})`)
        .attr('transform', `translate(0, ${height - margin.bottom})`)
        .call(d3.axisBottom(x).tickSizeOuter(0));
    };

    const yAxis = (g) =>
      g
        .attr('transform', `translate(${margin.left}, 0)`)
        .call(d3.axisLeft(y).tickValues([0, 20, 40, 60, 80, 100]).tickSize(-width))
        .call((g) => g.select('.domain').remove())
        .attr('class', 'grid');

    // apply axis to canvas
    svg.append('g').call(xAxis);
    svg.append('g').call(yAxis);

    // vertical bar chart
    svg
      .append('g')
      .selectAll('rect')
      .data(data)
      .enter()
      .append('rect')
      .attr('x', (data) => x(data.month) + x.bandwidth() / 2 - 10)
      .attr('y', (data) => y(data.value))
      .attr('width', 20)
      .attr('height', (data) => y(0) - y(data.value))
      .attr('class', 'bar-chart')
      .attr('fill', (data) => data.color);

    //line chart
    const line = d3
      .line()
      .x((d) => x(d.month) + x.bandwidth() / 2)
      .y((d) => y(d.value));

    svg
      .append('path')
      .datum(data)
      .attr('fill', 'none')
      .attr('stroke', 'red')
      .attr('stroke-width', 1)
      .attr('d', line);

    // add text
    svg
      .append('g')
      .selectAll('text')
      .data(data)
      .enter()
      .append('text')
      .text((d) => d.value)
      .attr('x', (data) => x(data.month) + x.bandwidth() / 2)
      .attr('y', (data) => y(data.value) - 5)
      .attr('fill', 'black')
      .attr('font-family', 'Tahoma')
      .attr('font-size', '12px')
      .attr('text-anchor', 'middle');
  };

  return <></>;
};
export default D3;