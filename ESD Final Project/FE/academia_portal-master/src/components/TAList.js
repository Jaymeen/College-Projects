import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, withStyles, makeStyles, TextField, Button } from '@material-ui/core';

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
  addTaContainer: {
    marginTop: '20px',
    display: 'flex',
    alignItems: 'center',
  },
  addTaInput: {
    marginRight: '10px',
  },
});

const StyledTableCell = withStyles((theme) => ({
  head: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  body: {
    fontSize: 14,
  },
}))(TableCell);

const TAList = () => {
    const classes = useStyles();
    const [taList, setTAList] = useState([]);
    const [newTaRollNo, setNewTaRollNo] = useState('');
    const { courseId, facCode } = useParams(); // Extract facCode from route parameters
  
    // Fetch TA list when the component mounts or when courseId changes
    useEffect(() => {
      const fetchTAList = async () => {
        try {
          const response = await axios.post('http://localhost:8080/api/v1/faculty/ta-list', {
            courseId: courseId,
          });
  
          console.log('The TA list is: ', response.data);
          setTAList(response.data);
          if (response.data !== null) {
            console.log("Fetch successful from the TA list");
          } else {
            console.error('Not able to fetch TA list.');
          }
        } catch (error) {
          console.error('Failed with error: ', error.message);
        }
      };
  
      fetchTAList();
    }, [courseId]);
  
    const handleAddTa = async () => {
        try {
          const response = await axios.post('http://localhost:8080/api/home/addTa', {
            taRollNo: newTaRollNo,
            facultyCode: facCode,
            courseCode: courseId,
          });
      
          console.log('Add TA response: ', response);
          // Assuming the API returns a success status
          if (response.status === 200) {
            // Fetch the updated TA list to get the complete student details
            const updatedResponse = await axios.post('http://localhost:8080/api/v1/faculty/ta-list', {
              courseId: courseId,
            });
      
            console.log('The updated TA list is: ', updatedResponse.data);
            setTAList(updatedResponse.data);
          }
        } catch (error) {
          console.error('Failed to add TA with error: ', error.message);
        }
      };      

  return (
    <>
      <TableContainer component={Paper}>
        <Table className={classes.table}>
          <TableHead>
            <TableRow>
              <StyledTableCell>Gender</StyledTableCell>
              <StyledTableCell>Login ID</StyledTableCell>
              <StyledTableCell>Student ID</StyledTableCell>
              <StyledTableCell>Student Name</StyledTableCell>
              <StyledTableCell>Student Roll No</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {taList.map((course) => (
              <TableRow key={course.studentId} className={classes.tableRow}>
                <StyledTableCell>{course.gender}</StyledTableCell>
                <StyledTableCell>{course.loginId}</StyledTableCell>
                <StyledTableCell component="th" scope="row" className={classes.tableCell}>
                    {course.studentId}
                </StyledTableCell>
                <StyledTableCell>{course.studentName}</StyledTableCell>
                <StyledTableCell>{course.studentRollNo}</StyledTableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      {/* Add TA input and button */}
      <div className={classes.addTaContainer}>
        <TextField
          className={classes.addTaInput}
          label="New TA Roll No"
          value={newTaRollNo}
          onChange={(e) => setNewTaRollNo(e.target.value)}
        />
        <Button variant="contained" color="primary" onClick={handleAddTa}>
          Add New TA
        </Button>
      </div>
    </>
  );
};

export default TAList;

