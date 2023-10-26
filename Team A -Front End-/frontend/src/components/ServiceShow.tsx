import * as React from "react";
import { Show, SimpleShowLayout, TextField, DateField, RichTextField, NumberField, ReferenceManyField, Datagrid, Pagination } from 'react-admin';

export const ServiceShow = () => (
    <Show>
        <SimpleShowLayout>
        <TextField source="name" />
            <NumberField source="messageCount" />
            <TextField source="lastMessage" />
            <TextField source="lastMessageDate" />
            <ReferenceManyField reference="messages" target="service_id" pagination={<Pagination />} perPage={5}>
                <Datagrid  bulkActionButtons={false}>
                    <TextField source="text" />
                    <DateField source="published_at" />
                </Datagrid>
            </ReferenceManyField>
        </SimpleShowLayout>
    </Show>
);