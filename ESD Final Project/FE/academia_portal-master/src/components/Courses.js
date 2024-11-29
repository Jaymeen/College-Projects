import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { useParams } from 'react-router-dom';

import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, makeStyles } from '@material-ui/core';
import TAList from './TAList'; // Assuming TAList is the component for the TA list page

const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
  tableCell: {
    fontWeight: 'bold',
  },
  tableRow: {
    '&:hover': {
      backgroundColor: '#f5f5f5',
    },
  },
});

const Courses = () => {
  const classes = useStyles();
  const [courses, setCourses] = useState([]);
  const { username } = useParams();
  const facCode = username;

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const response = await axios.post('http://localhost:8080/api/v1/faculty/courses', {
          facCode: facCode,
        });

        console.log('the data is : ', response.data);
        setCourses(response.data);
        if (response.data !== null) {
          console.log("fetch successful from the courses");
        } else {
          console.error('Not able to fetch:');
        }
      } catch (error) {
        console.error('failed with error: ', error.message);
      }
    };

    fetchCourses();
  }, [facCode]);

  return (
    <TableContainer component={Paper}>
      <Table className={classes.table}>
        <TableHead>
          <TableRow>
            <TableCell className={classes.tableCell}>Course Code</TableCell>
            <TableCell className={classes.tableCell}>Course Name</TableCell>
            <TableCell className={classes.tableCell}>Specialization</TableCell>
            <TableCell className={classes.tableCell}>Credits</TableCell>
            <TableCell className={classes.tableCell}>Faculty</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {courses.map((course) => (
            <TableRow key={course.courseId} className={classes.tableRow}>
              {/* Use Link for courseCode */}
              <Link to={`/courses/${facCode}/${course.courseId}`}>
                <TableCell component="th" scope="row" className={classes.tableCell}>
                    {course.courseCode} 
                </TableCell>
              </Link>
              <TableCell className={classes.tableCell}>{course.courseName}</TableCell>
              <TableCell className={classes.tableCell}>{course.specialization}</TableCell>
              <TableCell className={classes.tableCell}>{course.credit}</TableCell>
              <TableCell className={classes.tableCell}>{course.faculty.name}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default Courses;
