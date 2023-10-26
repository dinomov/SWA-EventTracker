import {
  Admin,
  Resource,
  ListGuesser,
  EditGuesser,
  ShowGuesser,
} from "react-admin";

import dataProvider from "./dataProvider";
import { ApiList } from "./components/ApiList";
import { DisallowedApiList } from "./components/DisallowedApiList";
import { ApiCreate } from "./components/ApiCreate";
import { ServiceList } from "./components/ServiceList";
import { ServiceShow } from "./components/ServiceShow";

export const App = () => (
  <Admin dataProvider={dataProvider} >
    <Resource
      name="sources"
      list={ApiList}
      create={ApiCreate}
    />
    <Resource
      name="disallowedsources"
      options={{ label: "Disallowed" }}
      list={DisallowedApiList}
    />
    <Resource
      name="services"
      list={ServiceList}
      show={ServiceShow}
    />
  </Admin>
);
