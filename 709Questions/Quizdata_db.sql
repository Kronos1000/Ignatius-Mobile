
INSERT INTO Questions VALUES('What is server-side scripting?','IT709','A virtual representation of the real DOM.','Source code that is sent from a server to a browser. This code is executed by the browser on the client-side.','Code that is executed on a server to create dynamic pages. The browser is only sent the generated HTML page or requested data. The necessary data is often stored in a database.','Code that is executed on a server to create dynamic pages. The browser is only sent the generated HTML page or requested data. The necessary data is often stored in a database.');


INSERT INTO Questions VALUES('What is client-side scripting?','IT709','Source code that is sent from a server to a browser. This code is executed by the browser on the client-side.','Code that is executed on a server to create dynamic pages. The browser is only sent the generated HTML page or requested data. The necessary data is often stored in a database.','a way To store and manage data for a component. When this data changes, the component will re-render to display these changes.','Source code that is sent from a server to a browser. This code is executed by the browser on the client-side.');


INSERT INTO Questions VALUES('What does a status code of 400 represent?','IT709','Bad Request - The server will not process the request because of a perceived client error','Not-Found This is caused when the server cannot find the requested resource','Internal Server Error','Bad Request - The server will not process the request because of a perceived client error');


INSERT INTO Questions VALUES('What does a status code of 401 represent?','IT709','Not-Found This is caused when the server cannot find the requested resource','Ok the request was successfull','Unauthorized -- not completed due to missing the required credentials','Unauthorized -- not completed due to missing the required credentials');


INSERT INTO Questions VALUES('What does a status code of 403 represent?','IT709','Internal server','Forbidden - The server understands the request, but refuses to authorize it','Unauthorized -- not completed due to missing the required credentials','Forbidden - The server understands the request, but refuses to authorize it');


INSERT INTO Questions VALUES('What does a status code of 200 represent?','IT709','OK - The request was successful','Content not found ','Unauthorized -- not completed due to missing the required credentials','OK - The request was successful');


INSERT INTO Questions VALUES('What is the purpose of an HTTP PUT request?','IT709','DELETE data via an api','UPDATE data vis an api','ADD data via an api','UPDATE data vis an api');


INSERT INTO Questions VALUES('What is the purpose of an HTTP POST request?','IT709','DELETE data via an api','UPDATE data vis an api','ADD data via an api','ADD data via an api');


INSERT INTO Questions VALUES('What is the purpose of an HTTP GET request?','IT709','RETRIEVE data via an api','UPDATE data vis an api','ADD data via an api','RETRIEVE data via an api');


INSERT INTO Questions VALUES('What is the purpose of an HTTP DELETE request?','IT709','DELETE data via an api','RETRIEVE data vis an api','ADD data via an api','DELETE data via an api');


INSERT INTO Questions VALUES('What do React Components use to communicate with each other?','IT709','Hooks','JWT','props','props');


INSERT INTO Questions VALUES('How do you access props in a React class component?','IT709','By accessing props directly without any object.','By using the this.props object.','By importing props from the React library.','By using the this.props object.');


INSERT INTO Questions VALUES('What does DOM stand for?','IT709','Data Object Management.','Document Obejct Model',' Distributed Object Model','Document Obejct Model');


INSERT INTO Questions VALUES('What is the common name of the header that you should configure in a get request to pass a JWT bearer token?','IT709','//','Authorzation','Token','Authorzation');


INSERT INTO Questions VALUES('What is the purpose of state in React?','IT709','to handle  user interactions and events in a React component.','To store and manage data for a component.',' to establish communication between different React components.','To store and manage data for a component.');


INSERT INTO Questions VALUES('What is the name of the package that should be installed to provide additional routing functionality in React?','IT709','react-router-dom.','React Navigation.','React Route Manager','react-router-dom.');


INSERT INTO Questions VALUES('What is JSX?','IT709','A compnent of CSS framework','A syntax extension to JavaScript that allows you to describe what the UI should look like.','React Route Manager','A syntax extension to JavaScript that allows you to describe what the UI should look like.');


INSERT INTO Questions VALUES('What are hooks in React?','IT709','Functions that allow you to use React features like state, and utilise lifecycle methods without needing to define a class for a component.','Built-in methods in React for handling asynchronous operations.','Special components used for managing global state in React applications.','Functions that allow you to use React features like state, and utilise lifecycle methods without needing to define a class for a component.');


INSERT INTO Questions VALUES('How do you change the request method when using fetch()?','IT709','By specififying the method property in the fetch configuration','By using the setMethod() function provided by the fetch API','By specififying the method property in the fetch configuration','By specififying the method property in the fetch configuration');


INSERT INTO Questions VALUES('How can you send data in the body of a request using fetch?','IT709','//','By making an htp request','By specifying the body property in the fetch options as a JSON string','By specifying the body property in the fetch options as a JSON string');


INSERT INTO Questions VALUES('What is the purpose of the Authorization header in a fetch request?','IT709','To provide credentials with each request that are used to authenticate and authorize a user','By making an htp request','By specifying the body property in the fetch options as a JSON string','To provide credentials with each request that are used to authenticate and authorize a user');


INSERT INTO Questions VALUES('What is the purpose of the useNavigate hook in React Router?','IT709','To synchronise your React application with an external system.','To return a function that lets you navigate programmatically within your application.','To get access to information about the current location.','To return a function that lets you navigate programmatically within your application.');


INSERT INTO Questions VALUES('What is the purpose of the useParams hook in React router?','IT709','To get access to information about the current location.','To provide a reference to a value that isnt needed for rendering.','To get access to query parameters that were passed when routing to the element','To get access to query parameters that were passed when routing to the element');


INSERT INTO Questions VALUES('What is the purpose of the useState hook?','IT709','To provide a reference to a value that isnt needed for rendering.','To manage state in a React application by adding a state variable','To get access to query parameters that were passed when routing to the element','To manage state in a React application by adding a state variable');


INSERT INTO Questions VALUES('What is the purpose of the useRef hook?','IT709','To provide a reference to a value that isnt needed for rendering.','To manage state in a React application by adding a state variable','To get access to query parameters that were passed when routing to the element','To provide a reference to a value that isnt needed for rendering.');


INSERT INTO Questions VALUES(' The Purpose of the useRef hook is To provide a reference to a value that isnt needed for rendering. ','IT709','True','False','//','True');


INSERT INTO Questions VALUES('When creating a list of JSX objects using `array.map()`, what prop is required for each rendered element?','IT709','The Map Prop','The key prop','The array prop','The key prop');


INSERT INTO Questions VALUES('When creating a list of JSX objects using `array.map()`, The KEY Prop is required for each rendered element?','IT709','True','False','//','True');


INSERT INTO Questions VALUES('Why is the key prop required when rendering a list of JSX elements?','IT709','//','It enables creation of multiple Pages in a single page application that allow navigation without causing a page refresh by using client-side routing.','It helps React match up each list item with its corresponding component, which is important if your list can be re-ordered, or have items inserted or deleted.','It helps React match up each list item with its corresponding component, which is important if your list can be re-ordered, or have items inserted or deleted.');


INSERT INTO Questions VALUES('What hook do you use for programmatic navigation in React Router?','IT709','useState','useNavigate','useParams','useNavigate');
INSERT INTO Questions VALUES('What hook do you use to manage state in a React functional component?','IT709','useState','useRef','useParams','useState');
