import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import { StatusBar } from 'expo-status-bar';
import Navbar from "./components/Navbar";
import Search from "./screens/Search";
// import Login from "./screens/Login";
// import Register from "./screens/Register";
import Film from "./screens/Film";
import Home from "./screens/Home";
import Recommend from "./screens/Recommend";

const Stack = createStackNavigator();

export default function App() {
  return (
    <>
      <StatusBar style="light" />
      <NavigationContainer>
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
            name="Notifications" 
            component={Film} 
            options={{ headerShown: false }} 
          />
        </Stack.Navigator>
        <Navbar />
      </NavigationContainer>
    </>
  );
}
