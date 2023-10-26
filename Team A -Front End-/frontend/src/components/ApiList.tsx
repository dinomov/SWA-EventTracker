import { BooleanField, Datagrid, DateField, List, TextField, UrlField } from 'react-admin';

export const ApiList = (props) => (
    <List {...props} exporter={false} >
        <Datagrid bulkActionButtons={false}>
            <TextField source="id" />
            <UrlField source="url" />
            <TextField source="name" />
            <DateField source="createdAt" />
            <DateField source="updatedAt" />
        </Datagrid>
    </List>
);
