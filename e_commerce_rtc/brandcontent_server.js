var app = require('http').createServer(handler),
    io = require('socket.io').listen(app),
    fs = require('fs'),
    app_nm = 'DB NOTIFY APP',
    serverIP = '127.0.0.1',
    serverPort = 4528,
    mysql = require('mysql'),
    DB_HOST = serverIP,
    DB_NAME = 'nodejs',
    DB_USER = 'creator',
    DB_PASS = 'txtdat_creator',
    DB_PORT = 8889,
    connectionsArray = [],
    db_connection = mysql.createConnection({
        host        : DB_HOST,
        user        : DB_USER,
        password    : DB_PASS,
        database    : DB_NAME,
        port        : DB_PORT
    }, function(){console.log('Databse connected successfully :) ')}),
    POLLING_INTERVAL = 3000,
    pollingTimer,
    index_file = 'client.html';


// If there is an error connecting to the database
db_connection.connect(function(err) {
  // connected! (unless `err` is set)
  console.log( err );
});

// create a new nodejs server ( localhost:8000 )
app.listen(serverPort, function(){
    console.log(app_nm+' : Server running on : '+serverIP+':'+serverPort);
});

// on server ready we can load our index.html page
function handler ( req, res ) {
    fs.readFile( __dirname + '/' +index_file ,
        function ( err, data ){
        if ( err ) {
                console.log( err );
                res.writeHead(500);
                return res.end( 'Error loading '+index_file );
            }
            res.writeHead( 200 );
            res.end( data );
        }
    );
}


/*
*
* HERE IT IS THE COOL PART
* This function loops on itself since there are sockets connected to the page
* sending the result of the database query after a constant interval
*
*/
var pollingLoop = function () {
    
    // Make the database query
    var query = db_connection.query('SELECT * FROM users'),
        users = []; // this array will contain the result of our db query


    // set up the query listeners
    query
    .on('error', function(err) {
        // Handle error, and 'end' event will be emitted after this as well
        console.log( err );
        updateSockets( err );
        
    })
    .on('result', function( user_i ) {
        // it fills our array looping on each user row inside the db
        users.push( user_i );
    })
    .on('end', function(){
        // loop on itself only if there are sockets still connected
        if(connectionsArray.length>=1) {
            
            //We would need to write a script here that checks if the data being pulled has been modified (ie. {1. in length or size}, {2. in change in character (not case sensitive) } ) before sending data to all connected sockets.
            
            pollingTimer = setTimeout( pollingLoop, POLLING_INTERVAL );

            updateSockets({users:users, total:users.length});
        }
    });

};

// create a new websocket connection to keep the content updated without any AJAX request
io.sockets.on( 'connection', function ( socket ) {
    
    console.log('Number of connections:' + connectionsArray.length);
    // start the polling loop only if at least there is one user connected
    if (!connectionsArray.length) {
        pollingLoop();
    }
    
    socket.on('disconnect', function () {
        var socketIndex = connectionsArray.indexOf( socket );
        console.log('socket = ' + socketIndex + ' disconnected');
        if (socketIndex >= 0) {
            connectionsArray.splice( socketIndex, 1 );
        }
    });

    console.log( 'A new socket is connected!' );
    connectionsArray.push( socket );
    
});

//to show number of users
var updateSockets = function ( data ) {
    // store the time of the latest update
    data.time = new Date();
    // number of the users connected
    data.connections = connectionsArray.length;
    // send new data to all the sockets connected
    connectionsArray.forEach(function( tmpSocket ){
        //tmpSocket.volatile.emit( 'notification' , data );
        tmpSocket.emit('notification', data);
        //io.sockets.emit('notification');
        //console.log('Volatile Emission : "notification" - DONE .');
    });
};