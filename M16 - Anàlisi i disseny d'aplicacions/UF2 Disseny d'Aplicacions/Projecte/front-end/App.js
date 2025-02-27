import { NavigationContainer, useNavigationContainerRef } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import { StatusBar } from 'expo-status-bar';
import Navbar from "./components/Navbar";
import Search from "./screens/Search";
// import Login from "./screens/Login";
// import Register from "./screens/Register";
import { useEffect, useState } from 'react';
import Film from "./screens/Film";
import Home from "./screens/Home";
import Notifications from "./screens/Notifications";
import Profile from "./screens/Profile";
import Recommend from "./screens/Recommend";

const Stack = createStackNavigator();

export default function App() {
  const navigationRef = useNavigationContainerRef();
  const [routeName, setRouteName] = useState("Home");

  useEffect(() => {
    const unsubscribe = navigationRef.addListener('state', () => {
      const currentRoute = navigationRef.getCurrentRoute();
      setRouteName(currentRoute?.name);
    });

    return unsubscribe;
  }, [navigationRef]);

  return (
    <>
      <StatusBar style="light" />
      <NavigationContainer ref={navigationRef}>
        <Stack.Navigator initialRouteName="Home">
          <Stack.Screen 
            name="Home" 
            component={Home} 
            options={{ headerShown: false }} 
          />
          <Stack.Screen 
            name="Search" 
            component={Search} 
            options={{ headerShown: false }} 
          />
          <Stack.Screen 
            name="Recommend" 
            component={Recommend} 
            options={{ headerShown: false }} 
          />
          <Stack.Screen 
            name="Film"
            component={Film} 
            options={{ headerShown: false }} 
          />
          <Stack.Screen 
            name="Notifications"
            component={Notifications} 
            options={{ headerShown: false }} 
          />
          <Stack.Screen 
            name="Profile"
            component={Profile} 
            options={{ headerShown: false }} 
          />
        </Stack.Navigator>
        {routeName !== 'Recommend' && routeName !== 'Film' && <Navbar currentPage={routeName} />}
        </NavigationContainer>
    </>
  );
}