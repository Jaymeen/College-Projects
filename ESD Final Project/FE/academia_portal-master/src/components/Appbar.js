import * as React from 'react';
import {AppBar, Box, Toolbar, Typography, IconButton} from '@material-ui/core';
import MenuIcon from '@material-ui/icons/Menu'
import LoginPage from './LoginPage';
export default function Appbar() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar variant="dense">
          <IconButton edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" color="inherit" component="div">
            Faculty Login Page
          </Typography>
        </Toolbar>
      </AppBar>
      <LoginPage />
    </Box>
  );
}
