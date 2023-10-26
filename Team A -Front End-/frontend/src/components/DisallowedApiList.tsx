import { BooleanField, BulkUpdateButton, Datagrid, DateField, List, TextField, UrlField, useRefresh } from 'react-admin';
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import { Fragment } from 'react';
import * as React from 'react';
import { useMutation } from 'react-query';
import { useDataProvider, useRecordContext, Button, useUpdate } from 'react-admin';

const ApproveButton = () => {
    const refresh = useRefresh();
    const record = useRecordContext();
    const dataProvider = useDataProvider();
    const { mutate, isLoading } = useMutation(
        () => dataProvider.approveSource(record.id).then(()=>refresh())
    );
    return <Button startIcon={<ThumbUpIcon/>} label="Approve" onClick={() => {console.log(record); mutate();}} disabled={isLoading} />;
};

export const DisallowedApiList = (props) => (
    <List {...props} exporter={false} >
        <Datagrid bulkActionButtons={false}>
            <TextField source="id" />
            <UrlField source="url" />
            <TextField source="name" />
            <DateField source="createdAt" />
            <DateField source="updatedAt" />
            <ApproveButton />
        </Datagrid>
    </List>
);