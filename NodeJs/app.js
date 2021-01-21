const express = require('express');
const mysql = require('mysql');
const  ejs = require('ejs');


const port = 3000

var cnx = mysql.createConnection({
host : "localhost",
user : "root",
password :"",
database : "gestion_formation"
})
const app = express();
app.set('view engine', 'ejs');
app.use(express.static("/views/public"));

cnx.connect(function(error){
    if(!!error) console.log(error);
    else  console.log('Connected!')
});


app.get('/session/:id_session', (req, res) => {
    let id_session = req.params.id_session;

    const sql = `SELECT * FROM session where id_session = ${id_session}`;
    cnx.query(sql, (err, row) => {
           if (err) throw err;
           res.render('index', {
            
               row: row
           });
   
       })
   
   });
   app.get('/map', function (req, res) {
    res.render('map')
  })

   app.listen(port, () => {
    console.log(`http://localhost:${port}`)
  })