import { BooleanField, Datagrid, DateField, List, NumberField, ReferenceManyField, TextField, UrlField } from 'react-admin';

export const ServiceList = (props) => (
    <List {...props} exporter={false} >
        <Datagrid bulkActionButtons={false} rowClick="show">
            <TextField source="name" />
            <NumberField source="messageCount" />
            <TextField source="lastMessage" />
            <TextField source="lastMessageDate" />
        </Datagrid>
    </List>
);
